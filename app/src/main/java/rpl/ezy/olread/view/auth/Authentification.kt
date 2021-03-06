package rpl.ezy.olread.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_authentification.*
import rpl.ezy.olread.R
import rpl.ezy.olread.adapter.PagerAdapter
import rpl.ezy.olread.utils.ConstantUtils.ADMIN
import rpl.ezy.olread.utils.ConstantUtils.STATUS
import rpl.ezy.olread.utils.ConstantUtils.USER
import rpl.ezy.olread.utils.ConstantUtils.USER_ID
import rpl.ezy.olread.utils.SharedPreferenceUtils
import rpl.ezy.olread.view.admin.AcceptActivity
import rpl.ezy.olread.view.admin.DashboardActivity
import rpl.ezy.olread.view.user.UserActivity


class Authentification : AppCompatActivity() {

    private var sharedPreferences : SharedPreferenceUtils ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentification)

        sharedPreferences = SharedPreferenceUtils(this@Authentification)

        if (sharedPreferences!!.getIntSharedPreferences(USER_ID) != -1){
            if (sharedPreferences!!.getIntSharedPreferences(STATUS) == ADMIN){
                startActivity(Intent(this@Authentification, DashboardActivity::class.java))
                finish()
                return
            }
            if (sharedPreferences!!.getIntSharedPreferences(STATUS) == USER){
                startActivity(Intent(this@Authentification, UserActivity::class.java))
                finish()
                return
            }
        }

        signup()
        val window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.green_1)
    }

    fun signup() {
        pager_auth.adapter = PagerAdapter(supportFragmentManager)
        tab_auth.setupWithViewPager(pager_auth)
        pager_auth.setPagingEnabled(false)
    }

}
