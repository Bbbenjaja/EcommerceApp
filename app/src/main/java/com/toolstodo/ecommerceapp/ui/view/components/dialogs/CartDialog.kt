package com.toolstodo.ecommerceapp.ui.view.components.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.squareup.picasso.Picasso
import com.toolstodo.ecommerceapp.R
import com.toolstodo.ecommerceapp.databinding.AddCartDialogBinding
import com.toolstodo.ecommerceapp.domain.model.cart.ProductCart
import com.toolstodo.ecommerceapp.domain.model.product.Product

class CartDialog {

    private lateinit var binding: AddCartDialogBinding

    private lateinit var context: Context
    private lateinit var product: Product
    private lateinit var onAccept: (ProductCart) -> Unit
    private lateinit var dialog: Dialog

    fun createDialog(context: Context, product: Product, onAccept: (ProductCart) -> Unit): Dialog {

        this.context = context
        this.product = product
        this.onAccept = onAccept

        binding = AddCartDialogBinding.inflate(LayoutInflater.from(context))

        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)

        initUI()
        initListeners()

        return dialog
    }

    private fun initUI() {
        with(binding) {
            txtTotal.text = product.price.toString()
            txtStock.text = product.stock.toString()
            imgProduct.post {
                Picasso.get().load(product.thumbnail).resize(imgProduct.width, imgProduct.height)
                    .into(imgProduct)
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            btnAdd.setOnClickListener {

                var quantity = txtCounter.text.toString().toInt()

                quantity = if(quantity > product.stock){
                    product.stock
                }else{
                    quantity
                }

                onAccept(
                    ProductCart(
                        id = product.id,
                        price = product.price.toDouble(),
                        discountPercentage = product.discountPercentage,
                        quantity = quantity,
                        thumbnail = product.thumbnail,
                        title = product.title
                    )
                )
                dialog.dismiss()
            }

            txtCounter.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val text = p0.toString()
                    if (text.isNotEmpty()) {

                        var quantity = text.toInt()

                        when {
                            quantity <= 0 -> {
                                quantity = 1
                                txtCounter.setText(quantity.toString())
                            }

                            quantity > product.stock -> {
                                quantity = product.stock
                                txtCounter.setText(quantity.toString())
                            }
                        }

                        val totalText = quantity * product.price
                        txtTotal.text = totalText.toString()
                        txtCounter.setSelection(quantity.toString().length)
                    } else {
                        txtTotal.text = product.price.toString()
                    }

                }

                override fun afterTextChanged(p0: Editable?) {}
            })

            btnAddProduct.setOnClickListener {
                if (txtCounter.text.toString().isNotEmpty()) {
                    val counterText = "${txtCounter.text.toString().toInt() + 1}"

                    if (counterText.toInt() <= product.stock) {
                        txtCounter.setText(counterText)
                    }
                } else {
                    txtCounter.setText(context.getString(R.string.count_label))
                }
            }

            btnMinusProduct.setOnClickListener {
                if (txtCounter.text.toString().isNotEmpty()) {
                    val counterText = if (txtCounter.text.toString().toInt() - 1 < 1) {
                        "1"
                    } else {
                        "${txtCounter.text.toString().toInt() - 1}"
                    }
                    txtCounter.setText(counterText)
                } else {
                    txtCounter.setText(context.getString(R.string.count_label))
                }
            }
        }
    }

}