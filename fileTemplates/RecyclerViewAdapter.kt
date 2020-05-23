#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import de.scondoo.android.R

class ${NAME}(private val itemClick: (${ITEM_CLASS}) -> Unit) : RecyclerView.Adapter<${NAME}.ViewHolder>(),
    AnkoLogger {

    private var ${ITEM_NAME_PLURAL} = emptyList<${ITEM_CLASS}>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.${layout}, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindArticle(${ITEM_NAME_PLURAL}.elementAt(position))
    }

    override fun getItemCount() = ${ITEM_NAME_PLURAL}.size

    internal fun setItems(items: List<${ITEM_CLASS}>) {
        this.${ITEM_NAME_PLURAL} = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View, private val itemClick: (${ITEM_CLASS}) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindArticle(${ITEM_NAME_SINGULAR}: ${ITEM_CLASS}) {
            with(${ITEM_NAME_SINGULAR}) {
                itemView.title.text = title
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}