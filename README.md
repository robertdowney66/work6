# work6
项目具体内容，详见下列文档

>[接口文档地址](https://apifox.com/apidoc/shared-829764d9-4d96-453e-88a2-32ba6eeba575)

>[飞书文档地址](https://aapjwtb5tjk.feishu.cn/wiki/M9LIwUq9SidYe6kQ0F3caukVn3f?from=from_copylink)

项目结构
```
├─common
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─yuyu
│      │  │          └─common
│      │  │              ├─config
│      │  │              ├─domain
│      │  │              ├─exception
│      │  │              ├─interceptors
│      │  │              └─Utils
│      │  └─resources
│      │      └─META-INF
│      └─test
│          └─java
├─gateway
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─yuyu
│      │  │          └─gateway
│      │  │              ├─config
│      │  │              ├─filters
│      │  │              └─utils
│      │  └─resources
│      └─test
│          └─java
├─limit
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─yuyu
│      │  │          └─limit
│      │  │              ├─annotation
│      │  │              ├─aspect
│      │  │              ├─config
│      │  │              └─enums
│      │  └─resources
│      │      ├─lua
│      │      └─META-INF
│      └─test
│          └─java
├─transcation
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─yuyu
│      │  │          └─transaction
│      │  │              ├─controller
│      │  │              ├─domain
│      │  │              │  └─po
│      │  │              ├─mapper
│      │  │              └─service
│      │  │                  └─impl
│      │  └─resources
│      └─test
│          └─java
└─user
    └─src
        ├─main
        │  ├─java
        │  │  └─com
        │  │      └─yuyu
        │  │          └─user
        │  │              ├─config
        │  │              ├─controller
        │  │              ├─domain
        │  │              │  └─po
        │  │              ├─mapper
        │  │              ├─service
        │  │              │  └─impl
        │  │              └─utils
        │  └─resources
        └─test
            └─java
```
