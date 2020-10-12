# MVVM 練習作品

Load photo data from jsonplaceholder.
https://jsonplaceholder.typicode.com/photos

Java, MVVM, Databinding, LiveData, Okhttp, Gson, etc.

## AuthorPage (AuthorPageFragment)
* 顯示名稱、按鈕
* 按下按鈕跳至 PhotoViewerPage

## BrowserPage (BrowserPageFragment)
* 以格狀顯示 jsonplaceholder/photos 中所有 ```thumbnailUrl``` 的圖示，並於格中顯示 id, title
* 點選任一格跳入 PhotoPage

## PhotoPage (PhotoFragment)
* 以全頁顯示 PhotoViewerPage 所點選的圖示，並於中央顯示 id, title
* 點選畫面返回 PhotoViewerPage

## TODO
* 以 ListAdapter 取代 recyclerView 的 adapter
* 以在 activity 中，以 Listener 的方式給 fragments 切換 fragment
    * https://developer.android.com/guide/components/fragments.html#CommunicatingWithActivity
* 在 background thread 執行過多 request 時，會出現大量 ```AppData::create pipe(2) failed: Too many open files```
    * 分段載入？
    * 在已滑過的部分，取消正在執行的 background task？
* 以 network callback 更新 recyclerView 上的 imageView 時，若更新的是畫面以外的 imageView，會導致畫面上的 imageView 顯示到錯誤的色塊
    * 在 ViewHoler 中以 liveData 更新 bitmap？
* 是否需要管理 background thread 上限？
* 如何避免載入過多圖片時的 OOM 問題？

## Done
* ~~Add lruCache and DiskCache for web image~~
    * ~~目前已加入 lruCache，但會因為 GC 導致資料要重取，正在加入 DiskCache 中~~
    * 先前 lruCache 存放圖片大小數值設定錯誤，現已修正
* ~~PhotoPage 的 ViewModel 裡不應該出現 View components~~
* ~~Grid 裡增加 binding~~
    * http://www.ravirupareliya.com/blog/recyclerview-with-android-databinding/
* ~~修正相片瀏覽中，title 顯示過長的問題~~
