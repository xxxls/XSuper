XSuper
===================================

### 功能组件 [U]
* xsuper_status (多状态视图)
* xsuper_titlebar (标题栏)
* xsuper_utils (工具集)
* xsuper_adapter (适配器)
* xsuper_widget (控件集)
* xsuper_logger (日志库)（现阶段只是做了对外的接口，内部调用的`XLog`）

### 核心层 [S]
* xsuper_core (多个项目的核心层/组织下Android组的核心层)

### 基础层 [B]
* module_base (本项目的基础层)

### 业务层 [P]
* module_home (首页)
* module_example (示例模块)

### 其他
* versionPlugin（`Composing builds` 版本依赖管理）

---
### 备忘说明

* `mvvm` hilt+flow 初步封装完毕，网络请求初步可以调通，但viewModel与组件的交互待完善（网络请求加载弹窗，网络请求异常码弹窗或其他的处理）
* paging还是旧版paging2时封装的，由于升级了core模块网络请求模块，故注释掉了paging相关代码，等待重新对paging3的封装使用