// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GramatikaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GramatikaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GramatikaParser#r}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR(GramatikaParser.RContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatikaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(GramatikaParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatikaParser#operand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperand(GramatikaParser.OperandContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatikaParser#convert}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConvert(GramatikaParser.ConvertContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatikaParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(GramatikaParser.OperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatikaParser#data_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_type(GramatikaParser.Data_typeContext ctx);
}