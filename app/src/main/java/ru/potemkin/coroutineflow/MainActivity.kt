package ru.potemkin.coroutineflow


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.potemkin.coroutineflow.databinding.ActivityMainBinding
import ru.potemkin.coroutineflow.Lesson2.UsersActivity
import ru.potemkin.coroutineflow.crypto_app.CryptoActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonUsersActivity.setOnClickListener {
            startActivity(UsersActivity.newIntent(this))
        }
        binding.buttonCryptoActivity.setOnClickListener {
            startActivity(CryptoActivity.newIntent(this))
        }
    }
}