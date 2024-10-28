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
              console.log("treeNote", treeNode.mmId, treeNode.mmName)
              if (treeNode.id == $('#id').val()) {
                  layer.tips('自己不能作为父资源', `#${treeId}`,{time:2000})
                  return
              }
              parentPermission(treeNode.mmId, treeNode.mmName)
          }
      }
  }
  $.get(`${contextPath}menus`,res => {
    const permissionTree = $.fn.zTree.init($('#permissionTree'), opts, res.data)
    const parentId = $('#parentId').val()
      console.log('parentId', parentId)
      if (parentId) {
        const nodes = permissionTree.getNodesByParam('mmId', parentId, null)
        console.log('nodes',nodes)
          if (nodes.length) {
            $('#parentName').val(nodes[0].mmName)
        } else {
            $('#parentName').val('-')
        }
    } else {
        $('#parentName').val('-')
    }
  })
}

function parentPermission(parentId,parentName) {
    if (parentId) {
        $('#parentId').val(parentId)
        $('#parentName').val(parentName)
    } else {
        $('#parentId').val(null)
        $('#parentName').val('-')
    }
}
