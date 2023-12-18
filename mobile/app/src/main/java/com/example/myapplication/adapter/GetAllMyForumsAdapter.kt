import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ForumListBinding
import com.example.myapplication.retrofit.GetAllMyForumsDto

class GetAllMyForumsAdapter : ListAdapter<GetAllMyForumsDto, GetAllMyForumsAdapter.Holder>(Comparator()) {

    private lateinit var onForumClickListener: (String) -> Unit
    private lateinit var onLeaveBtnClickListener: (String) -> Unit

    fun setOnForumClickListener(onForumClickListener: (String) -> Unit) {
        this.onForumClickListener = onForumClickListener
    }

    fun setOnLeaveForumClickListener (onLeaveBtnClick: (String) -> Unit) {
        this.onLeaveBtnClickListener = onLeaveBtnClick
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ForumListBinding.bind(view)

        fun bind(getAllMyForumsDto: GetAllMyForumsDto) = with(binding) {
            allForumsForumTitle.text = getAllMyForumsDto.theme
            allForumsForumDescription.text = getAllMyForumsDto.description

            leaveForumBtn.setOnClickListener {onLeaveBtnClickListener(getAllMyForumsDto.id)}
            itemView.setOnClickListener { onForumClickListener(getAllMyForumsDto.id) }
        }
    }

    class Comparator : DiffUtil.ItemCallback<GetAllMyForumsDto>() {
        override fun areItemsTheSame(oldItem: GetAllMyForumsDto, newItem: GetAllMyForumsDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GetAllMyForumsDto, newItem: GetAllMyForumsDto): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.forum_list, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}
