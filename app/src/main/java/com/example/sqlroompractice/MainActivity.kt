package com.example.sqlroompractice

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.sqlroompractice.adapter.NewsAdapter
import com.example.sqlroompractice.database.AppDatabase
import com.example.sqlroompractice.databinding.ActivityMainBinding
import com.example.sqlroompractice.databinding.DialogBinding
import com.example.sqlroompractice.entity.News

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var appDatabase: AppDatabase

    lateinit var list: ArrayList<News>

    lateinit var newsAdapter: NewsAdapter

    var ID = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)

        list = ArrayList()
        list.addAll(appDatabase.newsDao().getAllNews())

        newsAdapter = NewsAdapter(list, object : NewsAdapter.OnItemClickListener {
            override fun onItemDelete(news: News, position: Int) {

                appDatabase.newsDao().deleteNews(news)
                newsAdapter.notifyItemRemoved(position)
                newsAdapter.notifyItemRangeChanged(position, list.size)

                list.remove(news)

            }

            override fun onItemEdit(news: News, position: Int) {

                val dialog = AlertDialog.Builder(this@MainActivity)
                val dialogBinding = DialogBinding.inflate(layoutInflater)
                dialog.setView(dialogBinding.root)

                dialogBinding.titleEt.setText(news.title)
                dialogBinding.descEt.setText(news.desc)

                dialog.setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                        val title = dialogBinding.titleEt.text.toString()
                        val desc = dialogBinding.descEt.text.toString()

                        news.title = title
                        news.desc = desc

                        appDatabase.newsDao().updateNews(news)
                        newsAdapter.notifyItemChanged(position)

                    }

                })

                dialog.show()

            }

        })

        binding.rv.adapter = newsAdapter

        binding.addBtn.setOnClickListener {

            val title = binding.edit1.text.toString()
            val desc = binding.edit2.text.toString()

            val news = News()
            news.title = title
            news.desc = desc

            Toast.makeText(this, "${list.size}", Toast.LENGTH_SHORT).show()

            if (list.size == 0){

                appDatabase.newsDao().addNews(news)
                list.add(news)
                newsAdapter.notifyItemInserted(list.size)

                Toast.makeText(this, "NOLINCHI", Toast.LENGTH_SHORT).show()

            }

            for (i in 0 until list.size) {

                if (list[i].title == title && list[i].desc == desc) {

                    //Toast.makeText(this, "Change dates", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, "${appDatabase.newsDao().getNewById(title, desc)}", Toast.LENGTH_SHORT).show()


                } else {

                    appDatabase.newsDao().addNews(news)
                    list.add(news)
                    newsAdapter.notifyItemInserted(list.size)

                    Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()

                }

            }

            //id ni chaqirish
            /*val id = appDatabase.newsDao().getNewById(title, desc)

            news.id = id*/

            //Toast.makeText(this, "id -> $id", Toast.LENGTH_SHORT).show()

        }

    }

}