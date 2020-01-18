import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.roland.howtoandroid.R
import org.json.JSONArray

class WeatherListAdapter : RecyclerView.Adapter<WeatherListAdapter.ItemViewHolder>() {
    private var content : JSONArray? = null

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): WeatherListAdapter.ItemViewHolder {
        // Create a new view. We'll use our own layout so we don't have
        // to set things like margin through code
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_list_item, parent, false)

        return ItemViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Grab the data at that position
        val itemData = content?.getJSONObject(position)

        // Start populating the data into our view
        holder.itemView.findViewById<TextView>(R.id.time_text).text = itemData?.getString("startTime")
    }

    // Return the size of your dataset (invoked by the layout manager)
    // We might not have content so we need to default it to length 0
    override fun getItemCount() = content?.length() ?: 0

    // Create a custom function to manually update the contents
    fun setItems(content: JSONArray) {
        this.content = content
        notifyDataSetChanged()
    }
}