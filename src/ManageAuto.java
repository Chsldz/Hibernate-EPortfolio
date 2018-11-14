import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageAuto {
	private static SessionFactory factory;

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();

		ManageAuto MA = new ManageAuto();

		// Hinzufügen einiger Autos
		Integer autoID1 = MA.addAuto("Fiat", "Punto", 50, "grün");
		Integer autoID2 = MA.addAuto("VW", "T6", 110, "weiß/rot");
		Integer autoID3 = MA.addAuto("Audi", "Q8", 300, "gelb");

		// Alle Autos auslesen
		MA.listAutos();

		// Updaten eines Autos
		MA.updateAuto(autoID3, 500);

		// Löschen eines Autos
		MA.deleteAuto(autoID1);

		// Alle Autos auslesen
		MA.listAutos();
	}

	// Methode um ein Auto in der DB zu erstellen
	public Integer addAuto(String marke, String model, int leistung, String farbe) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer autoID = null;

		try {
			tx = session.beginTransaction();
			Auto auto = new Auto(marke, model, leistung, farbe);
			autoID = (Integer) session.save(auto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return autoID;
	}

	// Methode um alle Autos in der DB zu lesen
	public void listAutos() {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List autos = session.createQuery("FROM Auto").list();
			for (Iterator iterator = autos.iterator(); iterator.hasNext();) {
				Auto auto = (Auto) iterator.next();
				System.out.print("Marke: " + auto.getMarke());
				System.out.print("  Model: " + auto.getModel());
				System.out.println("  Leistung: " + auto.getLeistung());
				System.out.println("  Farbe: " + auto.getFarbe());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	// Methode um die Leistung am Auto zu aktualisieren
	public void updateAuto(Integer AutoID, int leistung) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Auto auto = (Auto) session.get(Auto.class, AutoID);
			auto.setLeistung(leistung);
			session.update(auto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	// Methode um Autos von der DB zu löschen
	public void deleteAuto(Integer autoID) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Auto auto = (Auto) session.get(Auto.class, autoID);
			session.delete(auto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
