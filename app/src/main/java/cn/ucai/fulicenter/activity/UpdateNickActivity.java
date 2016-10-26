package cn.ucai.fulicenter.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.FuLiCenterApplication;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.bean.User;
import cn.ucai.fulicenter.net.NetDao;
import cn.ucai.fulicenter.utils.CommonUtils;
import cn.ucai.fulicenter.view.DisplayUtils;

public class UpdateNickActivity extends BaseActivity {

    @BindView(R.id.et_update_user_name)
    EditText metUpdateUserName;
    UpdateNickActivity mContext;

    User user=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_update_nick);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        DisplayUtils.initBackWithTitle(mContext,getResources().getString(R.string.update_user_nick));
    }

    @Override
    protected void initData() {
        user = FuLiCenterApplication.getUser();
        if(user!=null){
            metUpdateUserName.setText(user.getMuserNick());
            metUpdateUserName.setSelectAllOnFocus(true);
        }else {
            finish();
        }
    }

    @Override
    protected void setListener() {

    }

    @OnClick(R.id.btn_save)
    public void checkNick() {
        if(user!=null){
            //拿到新写入的昵称nick；
            String nick = metUpdateUserName.getText().toString().trim();
            if(nick.equals(user.getMuserName())){
                //吐司判断昵称和之前一样：未修改
                CommonUtils.showLongToast(R.string.update_nick_fail_unmodify);
            }else if (TextUtils.isEmpty(nick)){
                //判断新写入的昵称不能为空
                CommonUtils.showLongToast(R.string.nick_name_connot_be_empty);
            }else {
                updateNick(nick);
            }
        }
    }

    private void updateNick(String nick) {
//        NetDao.updateNick();
    }
}
