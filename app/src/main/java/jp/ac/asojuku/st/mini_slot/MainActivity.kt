package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import jp.ac.asojuku.st.mini_slot.GameActivity
import jp.ac.asojuku.st.mini_slot.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var have:Int =1000
    var bet:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        if(pref.getInt("HAVE_COIN",1000) - pref.getInt("BET_COIN",10) < 0){
            pref.edit().putInt("BET_COIN",pref.getInt("HAVE_COIN",1000)).apply()
        }
        have = pref.getInt("HAVE_COIN",1000) - pref.getInt("BET_COIN",10)
        havecoin1.text = have.toString()
        bet = pref.getInt("BET_COIN",10)
        betcoin2.text = bet.toString()
        betup.setOnClickListener(View.OnClickListener() {
            if(have >= bet +10) {
                have -= 10
                havecoin1.text = have.toString()
                bet += 10
                betcoin2.text = bet.toString()
            }
        })
        // だみーこめんと
        betdown.setOnClickListener(View.OnClickListener() {
            if(bet - 10 >= 10) {
                have += 10
                havecoin1.text = have.toString()
                bet -= 10
                betcoin2.text = bet.toString()
            }
        })
        resetbutton.setOnClickListener(){
            havecoin1.text = have.toString()
        }
        startbutton.setOnClickListener{onStartButtonTapped(it)};

        val editor = pref.edit()
        editor.apply()
    }
    // aaaa
    fun onStartButtonTapped(view: View?){
        val intent = Intent(this,GameActivity::class.java)
        intent.putExtra("BET_COIN",bet)
        intent.putExtra("HAVE_COIN",have)
        this.startActivity(intent);
    }
    fun dummy(){

    }
}