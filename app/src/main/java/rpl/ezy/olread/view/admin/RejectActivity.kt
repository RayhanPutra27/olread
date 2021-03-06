package rpl.ezy.olread.view.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_reject.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rpl.ezy.olread.R
import rpl.ezy.olread.adapter.AcceptedRecipesAdapter
import rpl.ezy.olread.api.GetDataService
import rpl.ezy.olread.api.RetrofitClientInstance
import rpl.ezy.olread.response.ResponseRecipes

class RejectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reject)

        bt_back.setOnClickListener {
            finish()
        }

        setRecycler()
    }

    fun setRecycler(){
        val service =
            RetrofitClientInstance().getRetrofitInstance().create(GetDataService::class.java)
        val call = service.getRejectedRecipe()
        call.enqueue(object : Callback<ResponseRecipes> {
            override fun onFailure(call: Call<ResponseRecipes>, t: Throwable) {
                Toast.makeText(
                    this@RejectActivity,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("LOGLOGAN", "${t.message}")
            }

            override fun onResponse(call: Call<ResponseRecipes>, response: Response<ResponseRecipes>) {
                if (response.body()!!.status == 200){
                    val data = response.body()!!.data

                    val mAdapter = AcceptedRecipesAdapter(this@RejectActivity, data)

                    rv_reject.apply {
                        layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                        adapter = mAdapter
                    }

                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        setRecycler()
    }
}
