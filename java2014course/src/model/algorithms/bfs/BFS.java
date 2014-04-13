package model.algorithms.bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import model.algorithms.AbsSearcher;
import model.algorithms.Action;
import model.algorithms.Distance;
import model.algorithms.Domain;
import model.algorithms.State;

public class BFS extends AbsSearcher {
	private Distance g;
	private Distance h;
	private Domain dom;

	public BFS(Domain domain) {
		this.dom = domain;
		g = new Distance() {
			@Override
			public double getDistance(State from, State to) {
				if (from.getState() == null) {
					return 0;
				}
				double g1 = Double.parseDouble(from.getState().split(",")[0])
						- Double.parseDouble(from.getCame_from().toString());
				double g2 = Double.parseDouble(from.getState().split(",")[1])
						- Double.parseDouble(from.getCame_from().toString());
				if (Math.abs(g1) == 1 && Math.abs(g2) == 1) {
					return from.getF() + 15;
				}
				return from.getF() + 10;
			}
		};

		h = new Distance() {
			@Override
			public double getDistance(State from, State to) {
				double h1 = from.getF() - to.getF();
				double h2 = from.getG() - to.getG();
				return h1 + h2;

			}
		};
	}

	@Override
	public ArrayList<Action> search(State start, State goal) {
		State q;
		State q_tag = null;
		double tentative_g_score;

		start.setG(0);
		start.setF(g.getDistance(start, start) + h.getDistance(start, goal));
		getState_openList().add(start);
		while (!getState_openList().isEmpty()) {
			q = getState_openList().poll();
			getState_closeList().add(q);
			if (q.equals(goal)) {
				return reconstruct_path(q, q.getCame_from());
			}
			for (Action action : dom.getActions(q)) {
				q_tag = action.doAction(q);
				tentative_g_score = q.getG() + dom.getRange(q, q_tag);
				if (getState_closeList().contains(q_tag.getState())
						&& tentative_g_score >= q_tag.getG()) {
					continue;
				} else {

					q_tag.setCame_from(q);
					q_tag.setG(tentative_g_score);
					getState_openList().add(q_tag);
				}
				if (tentative_g_score > g.getDistance(q_tag, goal)) {
					continue;
				}

				if (Double.parseDouble(q.getState()) < q_tag.getG()) {
					q_tag.setG(Double.parseDouble(q.getState()));
				}
				q_tag.setF(q_tag.getG() + h.getDistance(q_tag, goal));

				getState_openList().add(q_tag);

			}
		}
		return null;
	}

	private ArrayList<Action> reconstruct_path(State goal, State son) {
		if (son == null) {
			return null;
		}

		Stack<Action> stack = new Stack<Action>();
		while (son != null) {
			stack.add(dom.getAction(goal, son));
			goal = son;
			son = son.getCame_from();
		}

		ArrayList<Action> arryActions = new ArrayList<Action>();
		while (!stack.isEmpty()) {
			arryActions.add(stack.pop());
		}

		return arryActions;

	}

}
