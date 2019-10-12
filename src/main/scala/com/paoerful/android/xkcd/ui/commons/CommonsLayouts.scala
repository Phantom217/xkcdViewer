// package com.paoerful.android.xkcd.ui.commons

// import android.support.v7.widget.RecyclerView
// import android.widget.{ LinearLayout, TextView }
// import macroid.FullDsl._
// import macroid.{ ActivityContextWrapper, Ui }

// class HeaderLayoutAdapter(implicit ctx: ActivityContextWrapper)
//   extends HeaderAdapterStyles
// {
//   var headerName: Option[TextView] = slot[TextView]

//   val content: LinearLayout = layout

//   private def layout(implicit ctx: ActivityContextWrapper) = Ui.get(
//     l[LinearLayout](
//       w[TextView] <~ wire(headerName) <~ headerNameStyle
//     ) <~ headerContentStyle
//   )
// }

// class ViewHolderHeaderAdapter(adapter: HeaderLayoutAdapter)
//   (implicit ctx: ActivityContextWrapper)
//   extends RecyclerView.ViewHolder(adapter.content)
// {
//   val content: LinearLayout = adapter.content

//   hal headerName: Option[TextView] = adapter.headerName
// }
