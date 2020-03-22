package tw.org.iii.brad.brad16;

import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

public class BradInputStreamRequest extends Request<byte[]> {
    private Response.Listener<byte[]> listener;
    private Map<String,String> responseHeader;
    private Map<String,String> params;

    public BradInputStreamRequest(
            int method,
            String url,
            Response.Listener<byte[]> listener,
            @Nullable Response.ErrorListener errorListener,
            Map<String,String> params) {

        super(method, url, errorListener);
        this.listener = listener;
        this.params = params;
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        responseHeader = response.headers;
        return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(byte[] response) {
        listener.onResponse(response);
    }
}
