# MVVM 練習作品

Load photo data from jsonplaceholder.
https://jsonplaceholder.typicode.com/photos

Java, MVVM, Databinding, LiveData, Okhttp, Gson, etc.

## AuthorPage
* 顯示名稱、按鈕
* 按下按鈕跳至 PhotoViewerPage

## PhotoViewerPage
* 以格狀顯示 jsonplaceholder/photos 中所有 ```thumbnailUrl``` 的圖示，並於格中顯示 id, title
* 點選任一格跳入 PhotoPage

## PhotoPage
* 以全頁顯示 PhotoViewerPage 所點選的圖示，並於中央顯示 id, title
* 點選畫面返回 PhotoViewerPage

## TODO
* Add lruCache and DiskCache for web image
    * 目前已加入 lruCache，但會因為 GC 導致資料要重取，正在加入 DiskCache 中
* 以 ListAdapter 取代 recyclerView 的 adapter
* 以在 activity 中，以 Listener 的方式給 fragments 切換 fragment
    * https://developer.android.com/guide/components/fragments.html#CommunicatingWithActivity
* PhotoPage 的 ViewModel 裡不應該出現 View components
* Grid 裡增加 binding
    * http://www.ravirupareliya.com/blog/recyclerview-with-android-databinding/
* 修正相片瀏覽中，title 顯示過長的問題
