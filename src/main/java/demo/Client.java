package demo;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class Client {
	private static SessionFactory sf = HibernateUtil.getSessionfactory();
	
	
	public static void list(){
		Session session = null;
		try{
			session = sf.openSession();
			List<Dept> list = session.createQuery("select d from Dept d ").list();
			for (Dept dept : list) {
				System.out.println(dept);
					System.out.println("Employees  for dept " + dept.getDeptno());
				for (Emp emp : dept.getEmps()) {
					System.out.println("\t\t"+ emp);
				}	
			}
		}catch(Exception e ){
			System.out.println(e);
		}
		finally {
			session.close();
		}
	}
	public static void list1(){
		Session session = null;
		try{
			session = sf.openSession();
			List<Emp> list = session.createQuery("select e from Emp e").list();
			for (Emp emp : list) {
				System.out.println(emp);
				System.out.println("dept = "  + emp.getDepartment());
			}
		}catch(Exception e ){
			System.out.println(e);
		}
		finally {
			session.close();
		}
	}
	public static void add(){
		Session session = null;
		Transaction tx = null;
		try{
			session = sf.openSession();
			tx = session.beginTransaction();
			Dept d  = new Dept(40,"Fin","Blr");
			Emp e1= new Emp(6,"Six",6666);
			e1.setDepartment(d);
			/*e2.setDepartment(d);
			e3.setDepartment(d);*/
			session.save(d);
			/*session.save(e3);
			session.save(e2); */
			session.save(e1);
			
			tx.commit();
		}catch(Exception e ){
			tx.rollback();
			System.out.println(e);
		}
		finally {
			session.close();
			
		}
	}
	
	public static void main(String[] args) {
		update(20);
		sf.close();
	}

	public static void update(int deptno, int empno){
		Session session = null;
		Transaction tx =null;
		try{
			session = sf.openSession();
			tx = session.beginTransaction();
			Dept d = session.get(Dept.class,deptno);
			Emp e = session.get(Emp.class,empno);
			e.setDepartment(d);
			tx.commit();
			}
		catch(Exception e ){
			System.out.println(e);
		}
		finally {
			session.close();
		}
	}
	public static void update(int deptno){
		Session session = null;
		Transaction tx =null;
		try{
			session = sf.openSession();
			tx = session.beginTransaction();
			Dept d = session.get(Dept.class,deptno);
			System.out.println(d);
			Set<Emp> emps = d.getEmps();
			for (Emp emp : emps) {
				System.out.println(emp);
			}
			Emp e1 = emps.iterator().next();
			e1.setDepartment(null);
			Emp e2 = new Emp(77,"ddd",444);
			emps.remove(e1);
			emps.add(e2);
			tx.commit();
			}
		catch(Exception e ){
			System.out.println(e);
		}
		finally {
			session.close();
		}
	}
}
