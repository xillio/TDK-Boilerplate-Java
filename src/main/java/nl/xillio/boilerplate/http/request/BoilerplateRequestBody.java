package nl.xillio.boilerplate.http.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class BoilerplateRequestBody {

    private final String id;
    private final String jsonrpc;
    private final String method;
}
