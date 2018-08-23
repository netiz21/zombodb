/* Generated By:JJTree: Do not edit this line. ASTWith.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package llc.zombodb.query_parser;

import java.util.ArrayList;
import java.util.List;

public
class ASTWith extends llc.zombodb.query_parser.QueryParserNode {
  public ASTWith(int id) {
    super(id);
  }

  public ASTWith(QueryParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(QueryParserVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }

  public List<String> validateNestedPaths() {
    List<String> paths = validateNestedPaths(this, new ArrayList<>());
    if (paths.size() == 1)
      return paths;

    // validate that the paths are the same
    String base = null;
    for (String path : paths) {
      int idx = path.indexOf('.');
      if (idx > -1)
        path = path.substring(0, idx);

      if (base == null)
        base = path;
      else if (!base.equals(path))
        throw new RuntimeException("WITH operator must have same base path on both sides");
    }

    return paths;
  }

  private List<String> validateNestedPaths(QueryParserNode node, List<String> paths) {
    String path = node.getNestedPath();
    if (path != null) {
      if (!paths.contains(path))
        paths.add(path);
    }
    for (QueryParserNode child : node) {
      validateNestedPaths(child, paths);
    }

    return paths;
  }
}
/* JavaCC - OriginalChecksum=8fe83177096cb5813ff6a238373d44db (do not edit this line) */