package org.iMage.plugins;

/**
 * Abstract parent class for plug-ins for JMJRST
 *
 * @author Dominik Fuchss
 */
public abstract class PluginForJmjrst implements Comparable<PluginForJmjrst> {

  /**
   * Returns the name of this plug-in
   *
   * @return the name of the plug-in
   */
  public abstract String getName();

  /**
   * Returns the number of parameters of this plug-in
   *
   * @return the number of parameters of this plug-in
   */
  public abstract int getNumberOfParameters();

  /**
   * JMJRST pushes the main application to every subclass - so plug-ins are allowed to look at Main
   * as well.
   *
   * @param main
   *     JMJRST main application
   */
  public abstract void init(org.jis.Main main);

  /**
   * Runs plug-in
   */
  public abstract void run();

  /**
   * Returns whether the plug-in can be configured or not
   *
   * @return true if the plug-in can be configured.
   */
  public abstract boolean isConfigurable();

  /**
   * Open a configuration dialogue.
   */
  public abstract void configure();

  @Override
  public int compareTo(PluginForJmjrst otherPlugin) {
    if(this.getName().compareTo(otherPlugin.getName()) != 0)
      return this.getName().compareTo(otherPlugin.getName());
    return this.getNumberOfParameters() - otherPlugin.getNumberOfParameters();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.getNumberOfParameters();
    result = prime * result + ((this.getName() == null) ? 0 : this.getName().hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PluginForJmjrst other = (PluginForJmjrst) obj;
    if (getNumberOfParameters() != other.getNumberOfParameters())
      return false;
    if (getName() != other.getName()) {
      if (other.getName() != null)
        return false;
    } else if (!getName().equals(other.getName()))
      return false;
    return true;
  }
}
