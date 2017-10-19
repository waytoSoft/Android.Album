# 优化加载手机SD卡所有图片
### 需求-显示出手机中所有的照片，包括系统相册图片和其他目录下的所有图片，并按照时间倒叙排列，需求看似简单，实则隐藏着一系列的性能优化问题

#### 体验过几个APP，相比之下微信做的还是很不错的，加载速度非常快，用户体验很好，其他家的出现有卡顿、黑屏等现象。

### 优化策略（循环分页加载策略实现）
#### 通过分页的方式来一页一页的加载图片，直到所有的图片都加载完成

#### 1、查询图片的URI
       Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI、

#### 2、查询条件(注：你要查询的某个目录添加到查询参数中，如果要查询所有SD卡图片设置selection=null)
       String selection = MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME + " = '目录名称' "
   
#### 3、因为要实现分页,sortOrder不是简单的按照时间倒叙来排了，如下：
       String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC limit " + PageSize + " offset " + PageIndex * PageSize;
##### PageSize表示一次查询多少条(Demo中默认一次加载200张)， PageIndex表示查询页数，从0开始  
  
### Demo说明
#### 1、分页加载引用[PullToRefreshRecyclerView](https://github.com/waytoSoft/Android.PullToRefresRecycelView),这是自己封装的一个控件，包括下拉刷新 ，上拉刷新，上下拉刷新，禁用刷新四种模式，目前支持List、Grid、Gallery等类型
#### 2、加载图片引用[Glide](https://github.com/bumptech/glide),很牛B的一个图片加载库
#### 3、Demo使用MVP模式实现

### Demo截图

<div class="tutorial-mock">
  <img src="./s1.jpg" />
  <img src="http://facebook.github.io/react-native/img/TutorialMock2.png" />
</div>
