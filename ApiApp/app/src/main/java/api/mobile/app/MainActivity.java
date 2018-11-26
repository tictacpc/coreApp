package api.mobile.app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.api.aspire.domain.usecases.GetToken;

import java.util.UUID;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn;

    private GetToken getTokenCase;
    private CompositeDisposable disposable = new CompositeDisposable();

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn_profile);
        btn.setOnClickListener(this);

        getTokenCase = new GetToken();

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Load token service");

    }

    @Override
    public void onClick(View v) {

        progressDialog.show();

        String uniqueId = UUID.randomUUID().toString();

        disposable.add(
                getTokenCase.getTokenService(uniqueId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<String>() {
                            @Override
                            public void onSuccess(String tokenService) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Token service: " + tokenService, Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                progressDialog.dismiss();
                                Log.e("TAG", "onError: " + e.getMessage());
                            }
                        })
        );


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
