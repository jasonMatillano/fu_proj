"use strict";

import { TreeNode } from "./TreeNode";

export class Tree {
  
  /* #region Instance variables */
  private _nodes: TreeNode[] | null = new Array<TreeNode>();
  /* #endregion */

  constructor() {}

  /* #region Getters and Setters */
  set nodes(nodes: TreeNode[]) {
    this._nodes = nodes;
  }
  get nodes(): TreeNode[] {
    return this._nodes;
  }
  /* #endregion */

  /* #region Nethods */
  addNode(node: TreeNode) {
    this.nodes.push(node);
  }
  /* #endregion */
}
