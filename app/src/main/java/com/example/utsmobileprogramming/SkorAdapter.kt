import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.utsmobileprogramming.LeaderBoard
import com.example.utsmobileprogramming.R
import android.view.View
import android.widget.TextView

class SkorAdapter(private val heroes: List<LeaderBoard>) : RecyclerView.Adapter<SkorHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): SkorHolder {
        return SkorHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.leaderboard_item, viewGroup, false))
    }

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: SkorHolder, position: Int) {
        holder.bindHero(heroes[position])
    }
}

class SkorHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvHeroName = view.findViewById<TextView>(R.id.username_ld)

    fun bindHero(hero: LeaderBoard) {
        tvHeroName.text = hero.username
    }
}