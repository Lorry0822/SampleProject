package com.wj.sampleproject.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import cn.wj.android.base.adapter.FragVpAdapter
import cn.wj.android.base.adapter.creator
import cn.wj.android.base.ext.string
import cn.wj.android.base.utils.AppManager
import com.gyf.immersionbar.ktx.immersionBar
import com.tencent.mmkv.MMKV
import com.wj.sampleproject.R
import com.wj.sampleproject.base.ui.BaseActivity
import com.wj.sampleproject.constants.*
import com.wj.sampleproject.databinding.AppActivityMainBinding
import com.wj.sampleproject.ext.toSnackbarMsg
import com.wj.sampleproject.fragment.*
import com.wj.sampleproject.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.absoluteValue

class MainActivity
    : BaseActivity<MainViewModel, AppActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModel()

    /** 上次返回点击时间 */
    private var lastBackPressMs = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_main)

        // 初始化 MMKV
        MMKV.initialize(mContext)

        // 配置状态栏
        immersionBar {
            transparentStatusBar()
            fitsSystemWindows(false)
        }

        // 配置适配器
        mBinding.cvpMain.adapter = FragVpAdapter.newBuilder()
                .manager(supportFragmentManager)
                .creator {
                    count(5)
                    createFragment { position ->
                        when (position) {
                            TAB_MAIN_BOTTOM_HOMEPAGE -> HomepageFragment.actionCreate()
                            TAB_MAIN_BOTTOM_SYSTEM -> SystemFragment.actionCreate()
                            TAB_MAIN_BOTTOM_BJNEWS -> BjnewsFragment.actionCreate()
                            TAB_MAIN_BOTTOM_PROJECT -> ProjectFragment.actionCreate()
                            else -> MyFragment.actionCreate()
                        }
                    }
                }
                .build()
    }

    override fun onBackPressed() {
        // 当前时间
        val currentBackPressMs = System.currentTimeMillis()
        if ((currentBackPressMs - lastBackPressMs).absoluteValue > MAIN_BACK_PRESS_INTERVAL_MS) {
            // 间隔时间外，提示
            viewModel.snackbarData.value = R.string.app_press_again_to_exit.string.toSnackbarMsg()
            // 保存时间
            lastBackPressMs = currentBackPressMs
        } else {
            // 间隔时间内，退到后台
            moveTaskToBack(true)
        }
    }

    companion object {

        /**
         * 界面入口
         *
         * @param context Context 对象
         */
        fun actionStart(context: Context = AppManager.getContext()) {
            context.startActivity(Intent(context, MainActivity::class.java).apply {
                if (context !is Activity) {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            })
        }
    }
}