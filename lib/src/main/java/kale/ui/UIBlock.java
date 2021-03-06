package kale.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;

/**
 * @author Jack Tony
 * @date 2015/6/15
 * 1.需要在界面销毁时把回调停止
 */
public abstract class UIBlock {

    protected String TAG = getClass().getSimpleName();

    private View mRootView;

    private Activity mActivity;

    protected void attachActivity(Activity activity) {
        mActivity = activity;
        mRootView = mActivity.findViewById(getRootViewId());
        
        bindViews();
        beforeSetViews();
        setViews();
    }

    /**
     * @return fragment的根view
     */
    public abstract
    @IdRes
    int getRootViewId();

    /**
     * 找到所有的views
     */
    protected abstract void bindViews();

    /**
     * 在这里初始化设置view的各种资源，比如适配器或各种变量
     */
    protected void beforeSetViews() {}

    /**
     * 设置所有的view
     */
    protected abstract void setViews();


    public View getRootView() {
        return mRootView;
    }

    protected Activity getActivity() {
        return mActivity;
    }

    protected <T extends Activity> T getActivity(Class<T> cls) {
        return (T)mActivity;
    }

    /**
     * @return true if you want to shield back key
     */
    protected boolean onBackPressed() {
        return false;
    }

    protected void onDestroy() {
        mActivity = null;
        mRootView = null;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {}


    @SuppressWarnings("unchecked")
    protected final <E extends View> E getView(int id) {
        try {
            return (E) mRootView.findViewById(id);
        } catch (ClassCastException ex) {
            Log.e(TAG, "Could not cast View to concrete class.", ex);
            throw ex;
        }
    }
}
