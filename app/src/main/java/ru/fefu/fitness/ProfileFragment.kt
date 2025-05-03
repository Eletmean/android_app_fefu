package ru.fefu.fitness

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    companion object {
        const val TAG = "profile_Fragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val changePasswordButton = view.findViewById<com.google.android.material.button.MaterialButton>(R.id.changePasswordButton)
        val logOutButton = view.findViewById<com.google.android.material.button.MaterialButton>(R.id.logOutButton)

        changePasswordButton.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager

            val currentFragment = fragmentManager.findFragmentByTag(TAG)
            val newFragment = fragmentManager.findFragmentByTag("edit_password_fragment")
                ?: EditPassword()

            fragmentManager.beginTransaction().apply {
                if (currentFragment != null) hide(currentFragment)
                if (!newFragment.isAdded) {
                    add(R.id.fragment_container, newFragment, "edit_password_fragment")
                } else {
                    show(newFragment)
                }
                addToBackStack(null)
                commit()
            }
        }

        logOutButton.setOnClickListener {
            val intent = Intent(requireActivity(), WelcomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}
