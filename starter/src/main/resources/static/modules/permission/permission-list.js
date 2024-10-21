const opts = {
    url: `${contextPath}menus`,
    idField: 'mmId',
    method: 'get',
    parentIdField: 'mmParentId',
    treeShowField: "mmName",
    columns: [
        {
            title: '序号',
            width: 20,
            formatter: (val,row,index) => {
                return index + 1
            }
        },
        {
            title: 'id',
            field: 'mmId',
            visible: false
        },
        {
            title: '名称',
            field: 'mmName',
            width: 200
        },
        {
            title: '请求方式',
            field: 'mmMethod',
            width: 30
        },
        {
            title: '路径',
            field: 'mmPath',
            width: 200
        },
        {
            title: '标识',
            field: 'mmCode',
            width: 100
        },
        {
            title: '图标',
            field: 'mmIcon',
            width: 50,
            formatter: (val,row,index)=>{
                return `<span class="fa ${val}"></span>`
            }
        },
        {
            title: '类型',
            field: 'mmType',
            width: 50,
            formatter: (val,row,index)=>{
                return val === 1 ? '菜单':'按钮'
            }
        },
        {
            title: '操作',
            field: 'action',
            visible: false,
            width: 100
        }
    ]
}

$(() => {
  $.treeTable(opts)
})
