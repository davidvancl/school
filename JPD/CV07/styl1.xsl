<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>

    <xsl:template match="/">
        <seznam>
            <xsl:apply-templates select="uzivatele/osoba"/>
        </seznam>
    </xsl:template>

    <xsl:template match="uzivatele/osoba">
        <jmeno>
            <xsl:attribute name="id">
                <xsl:value-of select="@id"/>
            </xsl:attribute>
            <xsl:value-of select="jmeno"/></jmeno>
    </xsl:template>

</xsl:stylesheet> 