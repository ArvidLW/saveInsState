package com.study.lw.saveInsState;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
//import android.view.Window;
//import android.view.WindowManager;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * 注意Activity与AppCompatActivity的区别，前者没有titlebar后者有titlebar
         *
         * 屏幕旋转系统会自动销毁activity并重新创建
         *
         * 测试最后发现开始savedInstanceState是一个空对象，值为null
         * 当onSaveInstanceState方法执行后，保存相应的状态，这时savedInstanceState就不为空了
         * 例如，当启动后，再旋转屏幕时，saveInstanceState就从null变为非空了，在FragmentActivity中
         * 有相应的判断SavedInstanceState并进行相关操作的代码，具体作用不知道，大概是记录状态
         */
        //Bundle lwBundle= new Bundle();
        Bundle lwBundle=null;
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(savedInstanceState !=null)
        {
            Log.d("lwtest","savedInstanceState不为空");
        }
        System.out.println(lwBundle);
        //这里lwBundle不是空的
        if(lwBundle ==null)
        {
            Log.d("lwtest2","lwBundle is empty");
        }
        else{
            Log.d("lwtest2","lwBundle is not empty");

        }
        Log.d("d","sd");
        System.out.println(savedInstanceState);
        //当activitity被重新创建的时候，如果saveInstanceState不为空，和为空两个执得的操作一样吗，区别在哪
        super.onCreate(lwBundle);
        if(lwBundle ==null)
        {
            Log.d("lwtest3","lwBundle is empty");
        }
        else{
            Log.d("lwtest3","lwBundle is not empty");

        }
        /**
         *  requestWindowFeature();的取值
            1.DEFAULT_FEATURES：系统默认状态，一般不需要指定
            2.FEATURE_CONTEXT_MENU：启用ContextMenu，默认该项已启用，一般无需指定
            3.FEATURE_CUSTOM_TITLE：自定义标题。当需要自定义标题时必须指定。如：标题是一个按钮时
            4.FEATURE_INDETERMINATE_PROGRESS：不确定的进度
            5.FEATURE_LEFT_ICON：标题栏左侧的图标
            6.FEATURE_NO_TITLE：无标题
            7.FEATURE_OPTIONS_PANEL：启用“选项面板”功能，默认已启用。
            8.FEATURE_PROGRESS：进度指示器功能
            9.FEATURE_RIGHT_ICON:标题栏右侧的图标
        */
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //状态 判断
        if (savedInstanceState != null) {
            Log.d("HELLO", "HELLO：应用进程被回收后，状态恢复");
            String string = savedInstanceState.getString("username");
            if (string != null) {
                Log.d("HELLO", "HELLO:" + string);
            }
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lw","执行onstart");
    }


    /**
     * 当某个activity变得“容易”被系统销毁时，该activity的onSaveInstanceState就会被执行，
     * 除非该activity是被用户主动销毁的，例如当用户按BACK键的时候
     * 一个原则：即当系统“未经你许可”时销毁了你的activity，则onSaveInstanceState会被系统调用
     * 情景：
     * 1. 当用户按下HOME键时
     * 2. 长按HOME键，选择运行其他的程序时。
     * 3. 按下电源按键（关闭屏幕显示）时。
     * 4. 从activity A中启动一个新的activity时。
     * 5. 屏幕方向切换时，例如从竖屏切换到横屏时。
     * 以上情景触发该函数，并且开发者可以保存一些数据状态
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d("HELLO", "HELLO：当Activity被销毁的时候，不是用户主动按back销毁，例如按了home键");
        super.onSaveInstanceState(savedInstanceState);
       // savedInstanceState.putString("username", "initphp"); //这里保存一个用户名
    }

    /**
     * onSaveInstanceState方法和onRestoreInstanceState方法“不一定”是成对的被调用的，
     * onRestoreInstanceState被调用的前提是，
     * activity A“确实”被系统销毁了，而如果仅仅是停留在有这种可能性的情况下，
     * 则该方法不会被调用，例如，当正在显示activity A的时候，用户按下HOME键回到主界面，
     * 然后用户紧接着又返回到activity A，这种情况下activity A一般不会因为内存的原因被系统销毁，
     * 故activity A的onRestoreInstanceState方法不会被执行
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("HELLO", "HELLO:如果应用进程被系统咔嚓，则再次打开应用的时候会进入");
    }
}