package com.hyejis.unsplash_app_tutorial

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.hyejis.unsplash_app_tutorial.utils.Constants
import com.hyejis.unsplash_app_tutorial.utils.SEARCH_TYPE
import com.hyejis.unsplash_app_tutorial.utils.onMyTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_button_search.*

class MainActivity : AppCompatActivity() {

    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(Constants.TAG,"MainActivity - onCreated Called")

        //라디오그룹 가져오기
        search_term_radio_group.setOnCheckedChangeListener{_, checkedId ->
            when(checkedId){
                R.id.photo_search_radio_btn ->{
                    Log.d(Constants.TAG,"photo search click..")
                    search_term_text_layout.hint = "사진검색"
                    search_term_text_layout.startIconDrawable = resources.getDrawable(R.drawable.ic_baseline_photo_library_24, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.PHOTO
                }
                R.id.user_search_radio_btn ->{
                    Log.d(Constants.TAG,"user search click..")
                    search_term_text_layout.hint = "사용자검색"
                    search_term_text_layout.startIconDrawable = resources.getDrawable(R.drawable.ic_baseline_person_24, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.USER
                }
            }

            Log.d(Constants.TAG,"MainActivity - onCheckedChanged() called / currentType : $currentSearchType")
        }

        //텍스트가 변경이 되었을때 - extention
        search_term_edit_text.onMyTextChanged {
            if(it.toString().count() > 0){  //입력된 글자가 하나라도 있으면 버튼 보이기
                frame_search_btn.visibility = View.VISIBLE
                search_term_text_layout.helperText = " "
                //버튼이 안보이면 스크롤뷰를 올려주는 부분
                main_scrollview.scrollTo(0, 200)
            }else{
                frame_search_btn.visibility = View.INVISIBLE
                search_term_text_layout.helperText = "검색어를 입력해주세요"
            }
            if(it.toString().count() == 12){
                Toast.makeText(this,"검색어는 12자까지만 입력 가능합니다", Toast.LENGTH_SHORT).show()
            }
        }

        btn_search.setOnClickListener {
            Log.d(Constants.TAG,"search button click.. / currentType : $currentSearchType")
            this.handleSearchButtonUI()
        }

    }

    private fun handleSearchButtonUI(){
        btn_progress.visibility = View.VISIBLE
        btn_search.text = ""

        Handler().postDelayed({
            btn_progress.visibility = View.INVISIBLE
            btn_search.text = "검색"
        }, 1500)
    }

}