package com.example.lerword_mvvm.App.View.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.lerword_mvvm.App.Model.Word
import com.example.lerword_mvvm.App.View.Adapters.ButtonClickListener
import com.example.lerword_mvvm.App.View.Adapters.CardStackAdapter
import com.example.lerword_mvvm.App.ViewModel.MainViewModel
import com.example.lerword_mvvm.R
import com.example.lerword_mvvm.databinding.ActivityMainBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import org.koin.androidx.viewmodel.ext.android.viewModel

/*
 * Created by Skyain1 on 13.07.2023.
 */


class MainActivity : AppCompatActivity(), CardStackListener, ButtonClickListener {

    private lateinit var binding: ActivityMainBinding
    private val vm by viewModel<MainViewModel>()
    private lateinit var cardStackView: CardStackView
    private lateinit var CSLmanager: CardStackLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cardStackView = findViewById(R.id.card_stack_view)
        CSLmanager = CardStackLayoutManager(this@MainActivity, this)
        CSLmanager.setCanScrollHorizontal(true)
        CSLmanager.setCanScrollVertical(false)
        cardStackView.layoutManager = CSLmanager
        vm.RefreshWords()
        vm.ResultWords.observe(this) {
            getData(it)
        }
        vm.knowWords.observe(this) {
            if (it == 10) {
                setprogress(it)
                setRestart()
            } else {
                setprogress(it)
            }
        }
    }

    private fun getData(words: ArrayList<Word>) {
        cardStackView.adapter = CardStackAdapter(this@MainActivity, words, this)
        cardStackView.adapter?.notifyDataSetChanged()
    }

    fun showErrorToast(error: String) {
        Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
    }

    private fun setRestart() {
        cardStackView.visibility = View.INVISIBLE
        binding.Continue.visibility = View.VISIBLE
        binding.cont.setOnClickListener {
            cardStackView.visibility = View.VISIBLE
            binding.Continue.visibility = View.GONE
            for (i in 1..10) {
                val resourceId =
                    resources.getIdentifier("strip$i", "id", packageName)
                if (resourceId != 0) {
                    val imageView = findViewById<ImageView>(resourceId)
                    imageView.setImageResource(R.drawable.progress_unactive)
                }
            }
            vm.resetKnowWords()
            binding.stats.text = "Заучено 0 новых слов"
        }
    }

    fun setprogress(knowWords: Int) {
        binding.stats.text = "Заучено $knowWords новых слов"
        for (i in 1..knowWords) {
            val resourceId = resources.getIdentifier("strip$i", "id", packageName)
            if (resourceId != 0) {
                val imageView = binding.root.findViewById<ImageView>(resourceId)
                imageView.setImageResource(R.drawable.progress_active)
            }
        }
    }

    override fun onCardSwiped(direction: Direction?) {
        vm.cardSwipe(direction)
    }

    override fun onButtonRightClick() {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        CSLmanager.setSwipeAnimationSetting(setting)
        cardStackView.swipe()
    }

    override fun onButtonLeftClick() {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Left)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        CSLmanager.setSwipeAnimationSetting(setting)
        cardStackView.swipe()
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {}
    override fun onCardRewound() {}
    override fun onCardCanceled() {}
    override fun onCardAppeared(view: View?, position: Int) {}
    override fun onCardDisappeared(view: View?, position: Int) {}

}