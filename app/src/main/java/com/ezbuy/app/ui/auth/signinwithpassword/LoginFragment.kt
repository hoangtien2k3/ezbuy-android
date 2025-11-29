package com.ezbuy.app.ui.auth.signinwithpassword

import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.ezbuy.app.base.BaseFragment
import com.ezbuy.app.common.LoadingScreen
import com.ezbuy.app.common.changeFocusedInputTint
import com.ezbuy.app.common.launchAndRepeatStarted
import com.ezbuy.app.common.showToast
import com.ezbuy.app.ui.dialog.DialogArguments
import com.ezbuy.app.ui.dialog.DialogFragment
import com.ezbuy.common.utils.Resource
import com.ezbuy.domain.model.signinwithpassword.KeycloakToken
import com.ezbuy.app.R
import com.ezbuy.app.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {
    private val viewModel by viewModels<LoginViewModel>()
    private var isPasswordShowing: Boolean = false

    override fun setupViews() {
        with(binding) {
            with(viewModel) {
//        backButton.setOnClickListener {
//          findNavController().popBackStack()
//        }
                signUpText.setOnClickListener {
//          val action = SignInWithPasswordDirections.actionSignInWithPasswordToDialogFragment()
//          findNavController().navigate(action)
                }
                showHidePassword.setOnClickListener {
                    if (isPasswordShowing) {
                        showHidePassword.setImageResource(R.drawable.dark_theme)
                        passwordEditText.transformationMethod = PasswordTransformationMethod()
                        isPasswordShowing = false
                    } else {
                        showHidePassword.setImageResource(R.drawable.hide)
                        passwordEditText.transformationMethod = null
                        isPasswordShowing = true
                    }
                }
                passwordEditText.onFocusChangeListener =
                    View.OnFocusChangeListener { _, hasFocus ->
                        passwordEditText.changeFocusedInputTint(hasFocus)
                        if (hasFocus) {
                            showHidePassword.setColorFilter(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.text_color,
                                ),
                            )
                        } else {
                            if (passwordEditText.text.toString().isEmpty()) {
                                showHidePassword.setColorFilter(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.inactive_input,
                                    ),
                                )
                            }
                        }
                    }
                emailEditText.onFocusChangeListener =
                    View.OnFocusChangeListener { _, hasFocus ->
                        emailEditText.changeFocusedInputTint(hasFocus)
                    }
                signInBtn.setOnClickListener {
                    viewModel.loginEzbuy(emailEditText.text.toString(), passwordEditText.text.toString())
                }
            }
        }
    }

    override fun bindViewModel() {
        launchAndRepeatStarted { viewModel.signInViewModel.collect(::handleLoginCase) }
    }

    private fun handleLoginCase(response: Resource<KeycloakToken>) {
        when (response) {
            is Resource.Loading -> {
                LoadingScreen.displayLoading(requireContext(), false)
            }

            is Resource.Failure -> {
                LoadingScreen.hideLoading()
                requireActivity().showToast(
                    getString(R.string.error),
                    response.error.cause?.message ?: getString(R.string.error),
                    MotionToastStyle.ERROR,
                )
            }

            is Resource.Success -> {
                LoadingScreen.hideLoading()

                DialogFragment.Companion.newInstance(
                    DialogArguments(
                        getString(R.string.congratulations),
                        getString(R.string.successful_sign_in),
                        R.drawable.dialog_profile,
                        R.id.action_signInWithPassword_to_homeFragment,
                    ),
                ).show(childFragmentManager, DialogFragment::class.simpleName)
            }
        }
    }
}
