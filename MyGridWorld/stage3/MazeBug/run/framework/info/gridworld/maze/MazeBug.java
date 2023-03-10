package info.gridworld.maze;

import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;  // move方法中，下一步要走的位置
	public boolean isEnd = false;  // 是否到达终点（红色石头）
	/**
	 * 用于记录走过的路径，用于回退
	 * 用栈来实现，每次走到一个新的位置，就把当前位置压入栈中
	 * 当走到死路时，就从栈中弹出一个位置，回退到上一个位置
	 */
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	// 用于记录实际走过的路径，用于显示
	public Stack<Location> trueWay = new Stack<Location>();
	public Integer stepCount = 0;  // 走过的步数
	boolean hasShown = false;  // 是否已经显示了最终结果
	private int[] dirCount = {0,0,0,0};  // 记录方向选择次数的数组

	/**
	 * Constructs a MazeBug.
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		isEnd = false;
		trueWay = new Stack<Location>();
		crossLocation = new Stack<ArrayList<Location>>();
		stepCount = 0;
		hasShown = false;
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		// 第一步的话先把当前位置压入栈中
		if (stepCount == 0) {
			Location local = this.getLocation();
			ArrayList<Location> first = new ArrayList<Location>();
			first.add(local);
			trueWay.push(local);
			crossLocation.add(first);
		}
		boolean willMove = canMove();  // 判断是否可以移动
		if (isEnd == true) {  // 到达终点
			for( Location w : trueWay ){
				Grid g = getGrid();
				Actor act = (Actor) g.get(w);
				act.setColor(Color.GREEN);
			}
			if (hasShown == false) {  // 如果还没有显示结果
				String msg = stepCount.toString() + " steps";  // 显示走过的步数
				JOptionPane.showMessageDialog(null, msg);  // 弹出对话框显示结果
				hasShown = true;  // 已经显示了结果
			}
		} else if (willMove) {
			move();
			stepCount++;
		}
		else {
			back();
		}
	}

	/**
	 * Find all positions that can be move to.
	 *
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		// 获取当前网格
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		int[] dirs = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
		for (int d : dirs) {
			Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
			if (gr.isValid(neighborLoc)) {
				Actor neighbor = gr.get(neighborLoc);
				// 如果为红色石头
				if ((neighbor instanceof Rock) && neighbor.getColor().equals(Color.RED)) {
					next = neighborLoc;
					ArrayList<Location> target = new ArrayList<Location>();
					target.add(next);
					return target;
				}
				else if (neighbor == null) {  // 不能走为花的位置，除非回溯
					valid.add(neighborLoc);
				}
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 *
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		// 获取当前位置
		Location loc = getLocation();
		// 获取当前位置的所有可移动的位置
		ArrayList<Location> valid = getValid(loc);
		if (valid == null || valid.size() == 0) {
			return false;
		}
		return true;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = this.getLocation();
		ArrayList<Location> validNeighbors = getValid(loc);  // 获取当前位置的所有可移动的位置
		// 统计不同位置对应的选择次数
		int count[] = {0,0,0,0};  // 0:北，1:东，2:南，3:西
		int pos[] = {0,0,0,0};  // 0:北，1:东，2:南，3:西
		int i = 0;
		int sum = 0;
		for( Location got : validNeighbors ) {  // 遍历所有可移动位置
			int dir = loc.getDirectionToward(got);  // 获取当前位置到可移动位置的方向
			count[dir/90] += dirCount[dir/90];
			sum += dirCount[dir/90];
			pos[dir/90] = i;
			++i;
		}
		// 根据随机值落在的区间选择一个方向
		Random random = new Random();
		if(sum == 0) {
			sum = 1;
		}
		int number = (int)random.nextInt(sum);
		if(number < count[0]) {
			next = validNeighbors.get(pos[0]);
		}
		else if(number < count[0] + count[1]) {
			next = validNeighbors.get(pos[1]);
		}
		else if(number < count[0] + count[1] + count[2]) {
			next = validNeighbors.get(pos[2]);
		}
		else {
			next = validNeighbors.get(pos[3]);
		}
		// 更新选择方向的选择次数
		int dir = loc.getDirectionToward(next);
		dirCount[dir/90] += 1;
		// 判断next位置actor是否为到达终点
		Actor tmp = (Actor)gr.get(next);
		if( (tmp instanceof Rock) && tmp.getColor().equals(Color.RED) ) {
			isEnd = true;
			System.out.println("end");
		}
		moveTo(next);  // 移动到下一个位置
		this.setDirection(dir);  // 设置方向
		trueWay.push(next);  // 将下一个位置压入栈中
		// 将下一个位置放入栈中
		ArrayList<Location> tmp2 = crossLocation.pop();
		tmp2.add(next);
		crossLocation.push(tmp2);
		// 在crossLocation中添加下一个位置
		ArrayList<Location> tmp3 = new ArrayList<Location>();
		tmp3.add(next);
		crossLocation.push(tmp3);
		// 在loc位置放一朵花
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}

	/**
	 * Moves the bug backward, putting a flower into the location it previously
	 * occupied.
	 */
	public void back() {
		// 先判断是否可以回溯
		if( trueWay.size() == 0 ) {
			return;
		}
		trueWay.pop();
		crossLocation.pop();
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = this.getLocation();
		Location last = trueWay.peek();
		moveTo(last);
		this.setDirection(loc.getDirectionToward(last));
		stepCount++;
		// 更新方向选择次数
		int dir = last.getDirectionToward(loc);
		dirCount[dir/90]--;
		// 在loc位置放一朵花
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}

