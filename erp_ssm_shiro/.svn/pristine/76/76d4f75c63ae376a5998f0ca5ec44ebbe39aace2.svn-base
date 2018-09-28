package cn.test.sys.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 把简单的json集合对象 转到一标准json集合对象
 * @author LJH
 *
 */
public class TreeNodeBuilder {

	/**
	 * 使用二层循环处理
	 * @param treeNodes
	 * @return
	 * @param rootId 根节点的pid
	 */
	public static List<TreeNode> builder(List<TreeNode> treeNodes,Integer rootId) {
		List<TreeNode> nodes=new ArrayList<>();
		for (TreeNode treeNode1 : treeNodes) {
			if(treeNode1.getPid()==rootId){//说明当前对象是根节点
				nodes.add(treeNode1);
			}
			for (TreeNode treeNode2 : treeNodes) {
				if(treeNode2.getPid()==treeNode1.getId()){//说明treeNode2是treeNode1的子节点
					//把treeNode2加入treeNode1的children里面
					treeNode1.getChildren().add(treeNode2);
				}
			}
		}
		return nodes;
	}

}
