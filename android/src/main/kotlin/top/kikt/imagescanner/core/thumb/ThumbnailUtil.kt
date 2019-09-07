package top.kikt.imagescanner.core.thumb

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.animation.GlideAnimation
//import com.bumptech.glide.request.transition.Transition
import io.flutter.plugin.common.MethodChannel
import top.kikt.imagescanner.Asset
import top.kikt.imagescanner.core.ResultHandler
import java.io.ByteArrayOutputStream
import java.io.File

/**
 * Created by debuggerx on 18-9-27 下午2:08
 */
object ThumbnailUtil {

    fun getThumbnailByGlide(ctx: Context, path: String, width: Int, height: Int, result: MethodChannel.Result) {
        val resultHandler = ResultHandler(result)

        //glide 4+
//        Glide.with(ctx)
//                .asBitmap()
//                .load(File(path))
//                .into(object : CustomTarget<Bitmap>(width, height) {
//                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                        val bos = ByteArrayOutputStream()
//                        resource.compress(Bitmap.CompressFormat.JPEG, 100, bos)
//                        resultHandler.reply(bos.toByteArray())
//                    }
//
//                    override fun onLoadCleared(placeholder: Drawable?) {
//                        resultHandler.reply(null)
//                    }
//
//                    override fun onLoadFailed(errorDrawable: Drawable?) {
//                        resultHandler.reply(null)
//                    }
//                })

        //glide 3+
        Glide.with(ctx)                             //配置上下文
                .load(Uri.fromFile(File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .asBitmap()
//                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//缓存全尺寸
                .into(object : CustomTarget<Bitmap>(width, height) {
                    override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>?) {
                        val bos = ByteArrayOutputStream()
                        resource.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                        resultHandler.reply(bos.toByteArray())
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        resultHandler.reply(null)
                    }

                    override fun onLoadFailed(e: Exception, errorDrawable: Drawable?) {
                        resultHandler.reply(null)
                    }
                })
    }

    fun getThumbnailWithVideo(ctx: Context, asset: Asset, width: Int, height: Int, result: MethodChannel.Result) {
        val resultHandler = ResultHandler(result)

        //glide 4+
//        Glide.with(ctx)
//                .asBitmap()
//                .load(File(asset.path))
//                .into(object : CustomTarget<Bitmap>(width, height) {
//                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                        val bos = ByteArrayOutputStream()
//                        resource.compress(Bitmap.CompressFormat.JPEG, 100, bos)
//                        resultHandler.reply(bos.toByteArray())
//                    }
//
//                    override fun onLoadCleared(placeholder: Drawable?) {
//                        resultHandler.reply(null)
//                    }
//                })

        Glide.with(ctx)                             //配置上下文
                .load(Uri.fromFile(File(asset.path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .asBitmap()
//                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//缓存全尺寸
                .into(object : CustomTarget<Bitmap>(width, height) {
                    override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>?) {
                        val bos = ByteArrayOutputStream()
                        resource.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                        resultHandler.reply(bos.toByteArray())
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        resultHandler.reply(null)
                    }

                    override fun onLoadFailed(e: Exception, errorDrawable: Drawable?) {
                        resultHandler.reply(null)
                    }
                })
    }

}
