package com.xxxxls.example.ui.tools.store

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.SimpleItemBean
import com.xxxxls.example.common.BaseListFragment
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.utils.store.MMKVP
import com.xxxxls.utils.store.SP
import com.xxxxls.utils.store.SuperStore

/**
 * @author Max
 * @date 1/28/21.
 */
@Route(path = HomePaths.HOME_FRAGMENT_STORE_INDEX)
class StoreIndexFragment : BaseListFragment() {

    // --- mmkv
    private var testInt: Int by MMKVP("int", 2)

    private var testString: String by MMKVP("string", "12")

    private var testFloat: Float by MMKVP("float", 2.1f)

    private var testObject: TestParcelable by MMKVP("object", TestParcelable(-1, "null"))

    // --- sp

    private var testInt2: Int by SP("int", 2)

    private var testString2: String by SP("string", "12")

    private var testFloat2: Float by SP("float", 2.1f)

    // --- superStore

    private var testInt3: Int by SuperStore("int", 2, "user/group")

    private var testString3: String by SuperStore("string", "12", "user/group")

    private var testFloat3: Float by SuperStore("float", 2.1f, "user/group")

    private var testObject3: TestParcelable by SuperStore(
        "object",
        TestParcelable(-2, "null"),
        "user/group"
    )

    override fun onInitialize() {
        super.onInitialize()
        com.tencent.mmkv.MMKV.initialize(context)
    }

    override fun getTitle(): String {
        return "Store"
    }

    override fun getItems(): Array<SimpleItemBean> {
        return arrayOf(
            SimpleItemBean("int"),
            SimpleItemBean("double"),
            SimpleItemBean("string"),
            SimpleItemBean("object")
        )
    }

    override fun onItemClick(index: Int, item: SimpleItemBean) {
        when (index) {
            0 -> {
                logD("old:$testInt")
                testInt = System.currentTimeMillis().toInt()
                logD("new:$testInt")

                logD("\n------------\n")

                logD("old:$testInt2")
                testInt2 = System.currentTimeMillis().toInt()
                logD("new:$testInt2")

                logD("\n------------\n")

                logD("old:$testInt3")
                testInt3 = System.currentTimeMillis().toInt()
                logD("new:$testInt3")
            }
            1 -> {
                logD("old:$testFloat")
                testFloat = System.currentTimeMillis().toFloat()
                logD("new:$testFloat")

                logD("\n------------\n")

                logD("old:$testFloat2")
                testFloat2 = System.currentTimeMillis().toFloat()
                logD("new:$testFloat2")


                logD("\n------------\n")

                logD("old:$testFloat3")
                testFloat3 = System.currentTimeMillis().toFloat()
                logD("new:$testFloat3")
            }
            2 -> {
                logD("old:$testString")
                testString = System.currentTimeMillis().toString()
                logD("new:$testString")

                logD("\n------------\n")

                logD("old:$testString2")
                testString2 = System.currentTimeMillis().toString()
                logD("new:$testString2")

                logD("\n------------\n")

                logD("old:$testString3")
                testString3 = System.currentTimeMillis().toString()
                logD("new:$testString3")
            }
            3 -> {
                logD("old:$testObject")
                testObject = TestParcelable(testInt, testString)
                logD("new:$testObject")

                logD("\n------------\n")

                logD("old:$testObject3")
                testObject3 = TestParcelable(testInt3, testString3)
                logD("new:$testObject3")
            }
        }
    }
}