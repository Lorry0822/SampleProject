package cn.wj.android.base.ui.dialog

import android.os.Bundle
import cn.wj.android.base.mvvm.BaseMvvmViewModel

/**
 * Dialog 弹窗基类
 * - 添加 MVVM 模式的支持
 * - [mViewModel] 实现了 LifecycleObserver 接口，实现对 Dialog 生命周期的感知
 *
 * @param VM MVVM ViewModel 类，继承 [BaseMvvmViewModel]
 *
 * @author 王杰
 */
abstract class BaseMvvmLibDialog<VM : BaseMvvmViewModel>
    : BaseLibDialog() {

    /** 当前界面 ViewModel 对象 */
    abstract val mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 添加生命周期监听
        lifecycle.addObserver(mViewModel)
    }

    override fun onDestroy() {
        super.onDestroy()

        // 移除生命周期监听
        lifecycle.removeObserver(mViewModel)
    }
}