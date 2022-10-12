package com.topzonestudio.recycleviewwithdiff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.tiagohm.codeview.CodeView
import br.tiagohm.codeview.Language
import br.tiagohm.codeview.Theme
import java.io.File

class CodeVIewActivity : AppCompatActivity() {
    private lateinit var mCodeView:CodeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_view)

        mCodeView = findViewById(R.id.codeView)
        val ss:String = intent.getStringExtra("abc").toString()

        val textFile = File(ss)
        val allText = textFile.readText()



        mCodeView.setOnHighlightListener(object : CodeView.OnHighlightListener{
            override fun onStartCodeHighlight() {
                
            }

            override fun onFinishCodeHighlight() {
                
            }

            override fun onLanguageDetected(language: Language?, relevance: Int) {
                
            }

            override fun onFontSizeChanged(sizeInPx: Int) {
                
            }

            override fun onLineClicked(lineNumber: Int, content: String?) {
                
            }

        })
            .setOnHighlightListener(object: CodeView.OnHighlightListener{
                override fun onStartCodeHighlight() {
                    
                }

                override fun onFinishCodeHighlight() {
                    
                }

                override fun onLanguageDetected(language: Language?, relevance: Int) {
                    
                }

                override fun onFontSizeChanged(sizeInPx: Int) {
                    
                }

                override fun onLineClicked(lineNumber: Int, content: String?) {
                    
                }
            })
            .setTheme(Theme.AGATE)
            .setCode(allText)
            .setLanguage(Language.JAVA)
            .setWrapLine(true)
            .setFontSize(14F)
            .setZoomEnabled(true)
            .setShowLineNumber(true)
            .setStartLineNumber(9000)
            .apply()


    }
}

