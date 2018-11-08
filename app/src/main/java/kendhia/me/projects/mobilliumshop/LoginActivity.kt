package kendhia.me.projects.mobilliumshop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import android.support.design.widget.Snackbar
import android.util.Log
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : Activity() {

    private val TAG = "LOGINACTIVITY"

    private val RCSIGN = 1235

    private val mAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val currentUser = mAuth.currentUser

        signInButton.setOnClickListener { updateUI(currentUser)}

    }


    private fun updateUI(account: FirebaseUser?) {
        if (account == null) {
            signIn()
        } else {
            startHomeActivity()
        }
    }

    private fun startHomeActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RCSIGN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RCSIGN) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                if (task.isSuccessful) {
                    val account = task.getResult(ApiException::class.java)
                    val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
                    mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Log.d(TAG, "signInWithCredential:success")
                                val user = mAuth.currentUser
                                updateUI(user)
                            } else {
                                Log.w(TAG, "signInWithCredential:failure", task.exception)
                                showTryAgain()
                            }
                        }
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    showTryAgain()
                }

            } catch (e: ApiException) {
                Log.w(TAG, "signInResult:failed code=" + e.statusCode)
                showTryAgain()
            }
        }
    }

    private fun showTryAgain() {
        val snackbar = Snackbar.make(
            findViewById(R.id.loginActivityLayout),
            resources.getString(R.string.login_error),
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction(resources.getString(R.string.try_again)) {
            //try to login again
            signIn()
        }
        snackbar.show()

    }
}
