# Autumn mvc框架

## Autumn框架，借鉴Spring的思想实现了：
1.Ioc

2.bean容器管理

3.注解式声明MVC

4.类似springmvcr的DispatherServlet
     
## 主要模块介绍
1.ClassUtil用于加载指定目录下的class到Set中
     
2.annotation包中定义了4个注解，@Controller,@Service,@Action（类似Spring中的RequestMapping）,@Inject（Ioc）
     
3.ClassHelper用于获取所有的class，另外为了后面的使用方便，声明了获取service及controller class的方法

4.BeanHelper类似于Spring中的Bean容器，内部有一个Map，key是class，value是class对应的Instance
     
5.IocHelper顾名思义就是实现Ioc的类
