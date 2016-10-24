package cn.ucai.fulicenter.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.I;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.bean.Result;
import cn.ucai.fulicenter.net.NetDao;
import cn.ucai.fulicenter.net.OkHttpUtils;
import cn.ucai.fulicenter.utils.CommonUtils;
import cn.ucai.fulicenter.utils.L;
import cn.ucai.fulicenter.utils.MFGT;
import cn.ucai.fulicenter.view.DisplayUtils;

public class RegisterActivity extends BaseActivity {
    private static final String TAG=RegisterActivity.class.getSimpleName();

    @BindView(R.id.username)
    EditText musername;
    @BindView(R.id.nick)
    EditText mnick;
    @BindView(R.id.password)
    EditText mpassword;
    @BindView(R.id.confirm_password)
    EditText mconfirmPassword;
    @BindView(R.id.btn_register)
    Button mbtnRegister;
    //注册用变量
    String username;
    String nickname;
    String password;
    RegisterActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mContext=this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        DisplayUtils.initBackWithTitle(this, "账户注册");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @OnClick(R.id.btn_register)
    public void checkedInput() {
         username=musername.getText().toString().trim();
         nickname=mnick.getText().toString().trim();
         password=mpassword.getText().toString().trim();
        String confirmpwd = mconfirmPassword.getText().toString().trim();
        if(TextUtils.isEmpty(username)){
            CommonUtils.showShortToast(R.string.user_name_connot_be_empty);
            musername.requestFocus();
            return;
        } else if (!username.matches("[a-z]\\w{5,15}")) {
            CommonUtils.showShortToast(R.string.illegal_user_name);
            musername.requestFocus();
            return;
        }else if (TextUtils.isEmpty(nickname)){
            CommonUtils.showShortToast(R.string.nick_name_connot_be_empty);
            mnick.requestFocus();
            return;
        }else if (TextUtils.isEmpty(password)){
            CommonUtils.showShortToast(R.string.password_connot_be_empty);
            mpassword.requestFocus();
            return;
        }else if (TextUtils.isEmpty(confirmpwd)){
            CommonUtils.showShortToast(R.string.confirm_password_connot_be_empty);
            mpassword.requestFocus();
            return;
        }else if (!password.equals(confirmpwd)){
            CommonUtils.showShortToast(R.string.two_input_password);
            mpassword.requestFocus();
            return;
        }
        register();
    }

    private void register() {
        final ProgressDialog pd=new ProgressDialog(mContext);
        pd.setMessage(getResources().getString(R.string.registering));
        pd.show();
        NetDao.register(mContext, username, nickname, password, new OkHttpUtils.OnCompleteListener<Result>() {
            @Override
            public void onSuccess(Result result) {
                pd.dismiss();
                if(result==null){
                    CommonUtils.showShortToast(R.string.register_fail);
                }else {
                    if(result.isRetMsg()){
                        CommonUtils.showLongToast(R.string.register_success);
                        setResult(RESULT_OK,new Intent().putExtra(I.User.USER_NAME,username));
                        MFGT.finish(mContext);
                    }else {
                        CommonUtils.showLongToast(R.string.register_fail_exists);
                        musername.requestFocus();
                    }
                }
            }

            @Override
            public void onError(String error) {
                pd.dismiss();
                CommonUtils.showShortToast(R.string.register_fail);
                L.e(TAG,"register erro="+error);
            }
        });
    }
}
