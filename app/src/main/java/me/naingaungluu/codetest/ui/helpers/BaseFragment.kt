package me.naingaungluu.codetest.ui.helpers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<T : BaseViewModel> : Fragment() , BaseView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupListeners()
    }

    protected fun showSnackBar(msg : String) {
        Snackbar.make(requireView(), msg , Snackbar.LENGTH_SHORT).show()
    }

}