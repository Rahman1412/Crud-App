package com.example.crudapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.R
import com.example.crudapp.UserActivity
import com.example.crudapp.data.User
import com.google.android.material.button.MaterialButton

class UserAdapter(private val onDelete: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val userList = mutableListOf<User>()

    fun setUsers(users: List<User>) {
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvName)
        val emailTextView: TextView = itemView.findViewById(R.id.tvEmail)
        val deleteButton: Button = itemView.findViewById(R.id.btnDelete)
        val idTextView : TextView = itemView.findViewById(R.id.userId)
        val editUser: MaterialButton = itemView.findViewById(R.id.editUser);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.nameTextView.text = user.name
        holder.emailTextView.text = user.email
        holder.idTextView.text = user.id.toString()
        holder.deleteButton.setOnClickListener { onDelete(user) }
        holder.editUser.setOnClickListener{
            val context = holder.itemView.context;
            val intent = Intent(context,UserActivity::class.java).apply {
                putExtra("id",user.id);
                putExtra("name",user.name);
                putExtra("email",user.email);
            }
            context.startActivity(intent);
        }
    }

    override fun getItemCount(): Int = userList.size
}
