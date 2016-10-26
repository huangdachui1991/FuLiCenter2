package cn.ucai.fulicenter.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.FuLiCenterApplication;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.bean.User;
import cn.ucai.fulicenter.dao.SharePrefrenceUtils;
import cn.ucai.fulicenter.utils.CommonUtils;
import cn.ucai.fulicenter.utils.ImageLoader;
import cn.ucai.fulicenter.utils.MFGT;
import cn.ucai.fulicenter.view.DisplayUtils;

public class UserProfileActivity extends BaseActivity {

    @BindView(R.id.iv_user_profile_avatar)
    ImageView mivUserProfileAvatar;
    @BindView(R.id.tv_user_profile_name)
    TextView mtvUserProfileName;
    @BindView(R.id.tv_user_profile_nick)
    TextView mtvUserProfileNick;
    UserProfileActivity mContext;

    User user=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        mContext=this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        DisplayUtils.initBackWithTitle(mContext,getResources().getString(R.string.user_frofile));
    }

    @Override
    protected void initData() {
        user= FuLiCenterApplication.getUser();
        if(user!=null){
            ImageLoader.setAvatar(ImageLoader.getAvatarUrl(user), mContext, mivUserProfileAvatar);
            mtvUserProfileName.setText(user.getMuserName());
            mtvUserProfileNick.setText(user.getMuserNick());
        }else {
            finish();
        }
    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.layout_user_profile_avatar, R.id.layout_user_profile_username, R.id.layout_user_profile_nickname, R.id.btn_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_user_profile_avatar:
                break;
            case R.id.layout_user_profile_username:
                CommonUtils.showLongToast(R.string.username_connot_be_modify);
                break;
            case R.id.layout_user_profile_nickname:
                
                break;
            case R.id.btn_logout:
                //点击退出登录的方法
                logout();
                break;
        }
    }

    private void logout() {
        if(user!=null){
            SharePrefrenceUtils.getInstence(mContext).removeUser();
            FuLiCenterApplication.setUser(null);
            MFGT.gotoLogin(mContext);
        }
        finish();
    }
}
