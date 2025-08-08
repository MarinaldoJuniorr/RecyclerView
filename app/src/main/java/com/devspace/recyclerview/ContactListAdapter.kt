import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devspace.recyclerview.Contact
import com.devspace.recyclerview.R

// Adaptacao entre o data class e o item_list layout
class ContactListAdapter:
    ListAdapter<Contact, ContactListAdapter.ContactViewHolder>
        (ContactDiffUtils()) {

            private lateinit var onClickListener: (Contact) -> Unit   //Som de click no kotlin

    //criar um view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ContactViewHolder(view)
    }

    //bind - atrelar o dado com a UI
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact, onClickListener)
    }

    //executando a funcao de click

    fun setOnCLickListener(onClick: (Contact) -> Unit) {
        onClickListener = onClick
    }

    //view que segura os dados
    class ContactViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        private val tvName = view.findViewById<TextView>(R.id.tv_name)
        private val tvPhone = view.findViewById<TextView>(R.id.tv_phone)
        private val image = view.findViewById<ImageView>(R.id.iv_image)


        fun bind(contact: Contact, onClick: (Contact) -> Unit){
            tvName.text = contact.name
            tvPhone.text = contact.phone
            image.setImageResource(contact.icon)

            view.setOnClickListener {
                onClick.invoke(contact)
            }
        }
    }

    // compara a diferenca quando nossa lista é atualizada
    class ContactDiffUtils: DiffUtil.ItemCallback<Contact>(){
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.name == newItem.name
        }

    }
}