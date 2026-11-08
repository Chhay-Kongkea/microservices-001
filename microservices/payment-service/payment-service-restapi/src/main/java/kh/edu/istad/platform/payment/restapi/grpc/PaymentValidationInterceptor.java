
package kh.edu.istad.platform.payment.restapi.grpc;

import build.buf.protovalidate.ValidationResult;
import build.buf.protovalidate.Validator;
import build.buf.protovalidate.ValidatorFactory;
import build.buf.protovalidate.exceptions.ValidationException;
import com.google.protobuf.Any;
import com.google.protobuf.Message;
import com.google.rpc.Code;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import io.grpc.protobuf.StatusProto;
import org.springframework.grpc.server.GlobalServerInterceptor;
import org.springframework.stereotype.Component;

@Component
@GlobalServerInterceptor
public class PaymentValidationInterceptor implements ServerInterceptor {

    private final Validator validator;

    public PaymentValidationInterceptor() {
        this.validator = ValidatorFactory.newBuilder().build();
    }

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next
    ) {

        ServerCall.Listener<ReqT> delegate =
                next.startCall(call, headers);

        return new ForwardingServerCallListener
                .SimpleForwardingServerCallListener<>(delegate) {

            private boolean validationFailed;

            @Override
            public void onMessage(ReqT message) {

                if (validationFailed) {
                    return;
                }

                if (!(message instanceof Message protoMessage)) {
                    validationFailed = true;

                    call.close(
                            Status.INTERNAL.withDescription(
                                    "Request is not a Protobuf message"
                            ),
                            new Metadata()
                    );
                    return;
                }

                try {
                    ValidationResult result =
                            validator.validate(protoMessage);

                    if (!result.isSuccess()) {

                        validationFailed = true;

                        com.google.rpc.Status status =
                                com.google.rpc.Status.newBuilder()
                                        .setCode(Code.INVALID_ARGUMENT.getNumber())
                                        .setMessage("Request validation failed")
                                        .addDetails(Any.pack(result.toProto()))
                                        .build();

                        var exception =
                                StatusProto.toStatusRuntimeException(status);

                        call.close(
                                exception.getStatus(),
                                exception.getTrailers()
                        );

                        return;
                    }

                    super.onMessage(message);

                } catch (ValidationException e) {

                    validationFailed = true;

                    call.close(
                            Status.INTERNAL.withDescription(
                                    "Unable to evaluate request validation rules"
                            ),
                            new Metadata()
                    );
                }
            }

            @Override
            public void onHalfClose() {
                if (!validationFailed) {
                    super.onHalfClose();
                }
            }
        };
    }
}
