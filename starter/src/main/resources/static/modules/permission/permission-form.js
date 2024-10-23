$(() => {
    loadMenuTree()
})

function loadMenuTree() {
  const opts = {
      view: {
          showLine: true
      },
      check: {
          enable: false
      },
      data: {
          simpleData: {
              enable: true,
              idKey: 'mmId',
              pIdKey: 'mmParentId',
              rootPid: null
          },
          key: {
              name: 'mmName',
              title: 'mmName' // 鼠标放上去显示的名字
          }
      },
      callback: {
          onClick: (e, treeId, treeNode) => {
              e.preventDefault()
          }
      }
  }
  $.get(`${contextPath}menus`,res => {
    $.fn.zTree.init($('#permissionTree'), opts, res.data)
  })
}
