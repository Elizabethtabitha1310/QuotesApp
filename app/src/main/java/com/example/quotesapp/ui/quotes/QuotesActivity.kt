package com.example.quotesapp.ui.quotes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quotesapp.R
import com.example.quotesapp.data.Quote
import com.example.quotesapp.utilities.InjectorUtils

class QuotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        initializeUi()
    }
private  fun initializeUi(){
    val factory = InjectorUtils.provideQuotesViewModelFactory()
    val viewModel = ViewModelProviders.of(this, factory)
        .get(QuotesViewModel::class.java)

    val textViewQuotes = findViewById<TextView>(R.id.textView_quotes)
    val buttonAddQuote = findViewById<Button>(R.id.button_add_quote)
    val editTextQuote = findViewById<EditText>(R.id.editText_quote)
    val editTextAuthor = findViewById<EditText>(R.id.editText_author)

    viewModel.getQuotes().observe(this, Observer { quotes ->
        val stringBuilder = StringBuilder()
        quotes.forEach { quote ->
            stringBuilder.append("$quote\n\n")
        }
        textViewQuotes.text = stringBuilder.toString()
    })

    buttonAddQuote.setOnClickListener {
        val quote = Quote(editTextQuote.text.toString(), editTextAuthor.text.toString())
        viewModel.addQuotes(quote)
        editTextQuote.setText("")
        editTextAuthor.setText("")
    }
  }
}