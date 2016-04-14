//public class ImageIconFactory {
//	private Map<URL,ImageIcon> map = new HashMap<URL, ImageIcon>();
//	public synchronized ImageIcon getImageIcon(URL url) {
//		ImageIcon ret = null;
//		if ((ret = map.get(url)) == null) {
//			ret = new ImageIcon(Toolkit.getDefaultToolkit().createImage(url));
//			map.put(url, ret);
//		}            
//		return ret;
//	}
//	public synchronized void reload(URL url) {
//		ImageIcon icon = map.get(url);
//		if (icon != null) {
//			icon.setImage(Toolkit.getDefaultToolkit().createImage(url));
//		}
//	}
//
//	public synchronized void remove(URL url) {
//		map.remove(url);
//	}
//}
//
